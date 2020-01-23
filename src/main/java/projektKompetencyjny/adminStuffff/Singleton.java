package projektKompetencyjny.adminStuffff;

import projektKompetencyjny.doTabeliUwagi;
import projektKompetencyjny.event_uczen.doTabeliEvent;

public class Singleton {
    private static Singleton instance = new Singleton();
    private doTabeliUwagi selectedForEditUwagi;
    private doTabeliEvent selectedForEditEvent;

    public static Singleton getInstance() {
        return instance;
    }

    public doTabeliUwagi getSelectedForEditUwagi() {
        return selectedForEditUwagi;
    }

    public void setSelectedForEditUwagi(doTabeliUwagi selectedForEditUwagi) {
        this.selectedForEditUwagi = selectedForEditUwagi;
    }


    public doTabeliEvent getSelectedForEditEvent() {
        return selectedForEditEvent;
    }

    public void setSelectedForEditEvent(doTabeliEvent selectedForEditEvent) {
        this.selectedForEditEvent = selectedForEditEvent;
    }

}
