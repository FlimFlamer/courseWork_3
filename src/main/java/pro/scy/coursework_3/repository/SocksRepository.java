package pro.scy.coursework_3.repository;

import pro.scy.coursework_3.model.Socks;
import pro.scy.coursework_3.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {
    void save(SocksBatch socksBatch);
    int remove(SocksBatch socksBatch);
    Map<Socks, Integer> getAll();
}
