package pro.scy.coursework_3.service;

import pro.scy.coursework_3.model.Color;
import pro.scy.coursework_3.model.Size;
import pro.scy.coursework_3.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);
    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
