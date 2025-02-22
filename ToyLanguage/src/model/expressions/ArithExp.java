package model.expressions;
import exceptions.DivisionByZeroException;
import exceptions.TypeException;
import model.adt.MyIHeap;
import model.types.IType;
import model.values.IValue;
import model.values.IntValue;
import model.types.IntType;
import model.adt.MyIDictionary;
import exceptions.MyException;

public class ArithExp implements IExp{
    private IExp e1;
    private IExp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public IValue eval(MyIDictionary<String,IValue> tbl, MyIHeap<Integer, IValue> heap) throws MyException {
        IValue v1,v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op==1) return new IntValue(n1+n2);
                if (op==2) return new IntValue(n1-n2);
                if (op==3) return new IntValue(n1*n2);
                if (op==4) {
                    if (n2==0) throw new DivisionByZeroException("Division by zero is not allowed");
                    return new IntValue(n1/n2);
                }
            } else throw new TypeException("Second operand is not an integer");
        } else throw new TypeException("First operand is not an integer");
        return null;
    }
    @Override
    public IType typecheck(MyIDictionary<String,IType> typeEnv) throws MyException {
        IType t1, t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new IntType();
            } else throw new TypeException("Second operand is not an integer");
        } else throw new TypeException("First operand is not an integer");
    }

    @Override
    public String toString() {
        String s = "";
        if (op==1) s = " + ";
        if (op==2) s = " - ";
        if (op==3) s = " * ";
        if (op==4) s = " / ";
        return e1.toString() + s + e2.toString();
    }
    @Override
    public IExp deepcopy() {
        return new ArithExp(e1.deepcopy(), e2.deepcopy(), op);
    }
}
