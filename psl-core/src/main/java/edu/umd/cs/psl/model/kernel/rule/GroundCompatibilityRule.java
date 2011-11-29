/*
 * This file is part of the PSL software.
 * Copyright 2011 University of Maryland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.umd.cs.psl.model.kernel.rule;

import edu.umd.cs.psl.model.formula.Formula;
import edu.umd.cs.psl.model.kernel.GroundCompatibilityKernel;
import edu.umd.cs.psl.model.parameters.Weight;
import edu.umd.cs.psl.reasoner.function.FunctionTerm;

public class GroundCompatibilityRule extends AbstractGroundRule implements
		GroundCompatibilityKernel {
	
	public GroundCompatibilityRule(CompatibilityRuleKernel k, Formula f) {
		super(k, f);
	}

	@Override
	public Weight getWeight() {
		return ((CompatibilityRuleKernel) kernel).getWeight();
	}
	
	@Override
	public FunctionTerm getFunctionDefinition() {
		assert numGroundings>=0;
		return getFunction(numGroundings);
	}

	@Override
	public double getIncompatibility() {
		return numGroundings*getWeight().getWeight()*(1.0-getTruthValue());
	}
	
	@Override
	public double getIncompatibilityHessian(int parameterNo1, int parameterNo2) {
		assert parameterNo1==0 && parameterNo2==0;
		return 0;
	}

	@Override
	public double getIncompatibilityDerivative(int parameterNo) {
		assert parameterNo==0;
		return numGroundings*(1.0-getTruthValue());
	}
	
	@Override
	public String toString() {
		return "{" + getWeight().toString() + "} " + formula; 
	}
}
