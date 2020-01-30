package projektKompetencyjny;

import java.util.ArrayList;

public class setGlobalAdmin {

    static Admin admin;

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin1) {
        setGlobalAdmin.admin = admin1;
    }
}
