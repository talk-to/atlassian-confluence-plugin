package com.flock.app;

import static com.atlassian.confluence.util.GeneralUtil.getVersionNumber;

public class Constants {
  public static final String CONFLUENCE_VERSION = getVersionNumber();
  public static final String PLUGIN_VERSION = "1.0.1";
  private static final String PROJECT_PREFIX = "flock-for-confluence/";
  public static final String KEY_WEB_HOOK_URL = PROJECT_PREFIX + "key-web-hook-url";
}
