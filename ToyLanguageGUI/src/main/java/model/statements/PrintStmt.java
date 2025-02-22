package model.statements;

import exceptions.MyException;
import model.adt.MyIDictionary;
import model.expressions.IExp;
import model.state.PrgState;
import model.types.IType;
import model.values.IValue;

public class PrintStmt implements IStmt{
    private IExp exp;

    public PrintStmt(IExp exp) {
        this.exp = exp;
    }
    @Override
    public String toString() {
        return "print(" + exp.toString()+ ")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);

        return null;
    }
    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
    @Override
    public IStmt deepcopy() {
        return new PrintStmt(exp.deepcopy());
    }
}
