package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;

import java.util.Arrays;

public class AfkCMD extends WCCmd{

    public AfkCMD() {
        super("afk", "", Arrays.asList("away", "aefecá", "aefeka", "adefeka"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        user.toggleAFK();
    }
}
