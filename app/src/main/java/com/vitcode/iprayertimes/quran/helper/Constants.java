package com.vitcode.iprayertimes.quran.helper;


import com.vitcode.iprayertimes.splash.App;

import java.io.File;

public class Constants {
    public static final String AS_BASE_URL = "https://storage.googleapis.com/ninesol_servers_as/qibla_connect/afasay/";
    public static final String EU_BASE_URL = "https://storage.googleapis.com/ninesol_servers_eu/qibla_connect/afasay/";
    public static final String US_BASE_URL = "https://storage.googleapis.com/ninesol_servers/qibla_connect/afasay/";
    public static final String rootFolderQuran = "Muslim/Quran/";
    public static final File rootPathQuran = new File(App.get().getFilesDir(), rootFolderQuran);
}
