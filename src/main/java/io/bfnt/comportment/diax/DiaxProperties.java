package io.bfnt.comportment.diax;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

public class DiaxProperties extends NamedProperties {

    @Property(value = "diax.token.main")
    private String mainToken;
    @Property(value = "diax.token.aprilFools")
    private String aprilFoolsToken;
    @Property(value = "diax.token.felisCatV1")
    private String felisCatV1;
    @Property(value = "diax.token.felisCatV2")
    private String felisCatV2;

    public String getMainToken() {
        return mainToken;
    }

    public String getAprilFoolsToken() {
        return aprilFoolsToken;
    }

    public String getFelisCatV1() {
        return felisCatV1;
    }

    public String getFelisCatV2() {
        return felisCatV2;
    }
}
