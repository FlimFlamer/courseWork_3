package pro.scy.coursework_3.service;

import pro.scy.coursework_3.model.Color;
import pro.scy.coursework_3.model.Size;
import pro.scy.coursework_3.model.SocksBatch;

public interface SocksStoreService {

    void accept(SocksBatch socksBatch);
    int issuance(SocksBatch socksBatch);
    int reject(SocksBatch socksBatch);
    int getCount(Color color, Size size, int cottonMin, int cottonMax);
}
