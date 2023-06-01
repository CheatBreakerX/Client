package com.cheatbreaker.client.config.types;


import com.google.common.collect.Lists;

import java.util.List;

public class UnrecommendedServer {
    public final String displayName;
    public final String reasonForWarning;
    public final List<String> addressExpressions;

    // Default messages
    public static final String REASON_P2W = "This server is known to have P2W (pay-to-win) mechanics. This makes an u" +
            "nsatisfactory experience for most players: the fact of having a paywall infront of the fair experience t" +
            "hat we at CheatBreakerX and many others want.";

    public UnrecommendedServer(String displayName, String reasonForWarning, String... addressExpressions) {
        this.displayName = displayName;
        this.reasonForWarning = reasonForWarning;
        this.addressExpressions = Lists.newArrayList(addressExpressions);
    }

    public boolean matches(String targetAddress) {
        for (String exp : this.addressExpressions) {
            if (targetAddress.matches(exp)) {
                return true;
            }
        }

        return false;
    }
}
