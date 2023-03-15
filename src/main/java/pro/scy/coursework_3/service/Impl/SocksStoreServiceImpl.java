package pro.scy.coursework_3.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.scy.coursework_3.exception.ValidationException;
import pro.scy.coursework_3.model.Color;
import pro.scy.coursework_3.model.Size;
import pro.scy.coursework_3.model.Socks;
import pro.scy.coursework_3.model.SocksBatch;
import pro.scy.coursework_3.repository.SocksRepository;
import pro.scy.coursework_3.service.SocksStoreService;
import pro.scy.coursework_3.service.ValidationService;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksStoreServiceImpl implements SocksStoreService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    @Override
    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int issuance(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {

        if(!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }

        Map<Socks, Integer> socksMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()){
            Socks socks = socksItem.getKey();

            if(socks.getColor().equals(color) &&
               socks.getSize().equals(size) &&
               socks.getCottonPart() >= cottonMin &&
               socks.getCottonPart() <= cottonMax)
            {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if(!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
