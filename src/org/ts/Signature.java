package org.ts;

import org.ts.vars.TS_Structure;

@FunctionalInterface
public interface Signature {
	TS_Structure valueOf(String vts) throws CannotCastVarException;
}
