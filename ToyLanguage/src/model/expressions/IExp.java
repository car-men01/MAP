package model.expressions;

import model.adt.MyIDictionary;
import exceptions.MyException;
import model.values.IValue;

public interface IExp {
    IValue eval(MyIDictionary<String,IValue> tbl) throws MyException;
    IExp deepcopy();
}

