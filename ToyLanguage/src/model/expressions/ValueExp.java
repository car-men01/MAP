package model.expressions;

import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.IValue;

public class ValueExp implements IExp {
    private IValue value;

    public ValueExp(IValue value) {
        this.value = value;
    }
    @Override
    public IValue eval(MyIDictionary<String,IValue> tbl, MyIHeap<Integer, IValue> heap) {
        return value;
    }
    @Override
    public String toString() {
        return value.toString();
    }
    @Override
    public IExp deepcopy() {
        return new ValueExp(value.deepcopy());
    }
}
