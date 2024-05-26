package bll.validators;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, <a href="http://dsrl.coned.utcluj.ro/">...</a>
 * @Since: Apr 03, 2017
 */
public interface Validator<T> {

    public boolean validate(T t);
}
