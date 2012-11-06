package server.world.genome.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Command implements Node {

	protected List<Update> updates;
	protected Action action;

	public Command(List<Update> updates, Action action) {
		this.updates = updates;
		this.action = action;
	}
	
	public Iterator<Update> updates() {
		return updates.iterator();
	}
	
	public Action action() {
		return action;
	}

	@Override
	public int size() {
		int size = 0;
		for (Update update : updates)
			size += update.size();
		if (action != null)
			size += action.size();
		return size + 1;
	}

	@Override
	public int condSize() {
		int size = 0;
		for (Update update : updates)
			size += update.condSize();
		if (action != null)
			size += action.condSize();
		return size;
	}

	@Override
	public int exprSize() {
		int size = 0;
		for (Update update : updates)
			size += update.exprSize();
		if (action != null)
			size += action.exprSize();
		return size;
	}

	@Override
	public Command mutate(Random rand, Program prog, MutationInfo mi) {
		int size = size() - 1; //size is never zero
		int nodeNo = rand.nextInt(size);
		int updateNo = 0;
		for (Iterator<Update> itr = updates.iterator(); itr.hasNext(); updateNo++) {
			Update update = itr.next();
			int updateSize = update.size();
			if (nodeNo < updateSize) {
				//mutate this update
				List<Update> updates = new LinkedList<Update>(this.updates);
				updates.set(updateNo, update.mutate(rand, prog, mi));
				return new Command(updates, action);
			}
			else
				nodeNo -= updateSize;
		}
		if (action != null) {
			//mutate action
			return new Command(updates, action.mutate(rand, prog, mi));
		}
		throw new MutationException("No nodes to mutate in this command");
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		boolean printSpace = false;
		for (Update update : updates) {
			if (printSpace)
				sb.append(" ");
			else
				printSpace = true;
			update.prettyPrint(sb);
		}
		if (action != null) {
			if (printSpace)
				sb.append(" ");
			else
				printSpace = true;
			action.prettyPrint(sb);
		}
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		int condNo = rand.nextInt(condSize);
		for (Update update : updates) {
			int updateCondSize = update.condSize();
			if (condNo < updateCondSize)
				return update.randomCond(rand);
			else
				condNo -= updateCondSize;
		}
		if (action != null) {
			return action.randomCond(rand);
		}
		throw new MutationException("A condition expected but not found");
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		for (Update update : updates) {
			int ruleExprSize = update.exprSize();
			if (exprNo < ruleExprSize)
				return update.randomExpr(rand);
			else
				exprNo -= ruleExprSize;
		}
		if (action != null) {
			return action.randomExpr(rand);
		}
		throw new MutationException("An expression expected but not found");
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		prettyPrint(sb);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Command))
			return false;
		Command casted = (Command)o;
		if (this.updates.size() != casted.updates.size())
			return false;
		for (Iterator<Update> i1 = this.updates.iterator(), i2 = casted.updates.iterator();
				i1.hasNext() && i2.hasNext(); ) {
			Update u1 = i1.next();
			Update u2 = i2.next();
			if (!u1.equals(u2))
				return false;
		}
		return this.action == null && casted.action == null || this.action.equals(casted.action);
	}
}
