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
package edu.umd.cs.psl.optimizer.conic.program.graph.cosi;

import java.util.Iterator;

import edu.umd.cs.psl.optimizer.conic.program.graph.Node;

class IteratorProxy<TO extends Node> implements Iterator<TO> {
	protected COSIGraph graph;
	protected Iterator<? extends edu.umd.umiacs.dogma.diskgraph.core.Node> iterator;
	
	protected IteratorProxy(COSIGraph g, Iterator<? extends edu.umd.umiacs.dogma.diskgraph.core.Node> i) {
		graph = g;
		iterator = i;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TO next() {
		return (TO) COSINode.wrap(graph, iterator.next());
	}

	@Override
	public void remove() {
		iterator.remove();
	}
}