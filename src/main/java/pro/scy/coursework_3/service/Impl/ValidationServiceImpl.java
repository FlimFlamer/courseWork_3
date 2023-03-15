package pro.scy.coursework_3.service.Impl;

import org.springframework.stereotype.Service;
import pro.scy.coursework_3.model.Color;
import pro.scy.coursework_3.model.Size;
import pro.scy.coursework_3.model.SocksBatch;
import pro.scy.coursework_3.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {


    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null &&
                socksBatch.getQuantity() > 0 &&
                socksBatch.getSocks().getColor() != null &&
                socksBatch.getSocks().getSize() != null &&
                chekCotton(socksBatch.getSocks().getCottonPart(), socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        return color != null &&
                size != null &&
                chekCotton(cottonMin, cottonMax);
    }

    private boolean chekCotton(int cottonMin, int cottonMax) {
        return cottonMin >= 0 &&
                cottonMax <= 100;
    }
}
