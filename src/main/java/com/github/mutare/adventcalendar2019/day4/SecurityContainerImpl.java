package com.github.mutare.adventcalendar2019.day4;

import com.github.mutare.adventcalendar2019.day4.codeverifiers.CodeVerifier;

public class SecurityContainerImpl implements SecurityContainer {

    private CodeVerifier[] verifiers;

    public SecurityContainerImpl(CodeVerifier... verifiers) {
        this.verifiers = verifiers;
    }

    @Override
    public boolean check(int code) {
        for (CodeVerifier verifier : verifiers) {
            if (!verifier.verify(code)) {
                return false;
            }
        }
        return true;
    }

}
