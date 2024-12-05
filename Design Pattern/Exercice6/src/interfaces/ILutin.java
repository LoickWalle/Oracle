package interfaces;

import models.LutinCommCenter;

public interface ILutin {
    void notification(String message);
    void makeToy(IToy toy, LutinCommCenter lutinCommCenter);
}
