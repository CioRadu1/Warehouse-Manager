package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, <a href="http://dsrl.coned.utcluj.ro/">...</a>
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 150;

    public boolean validate(Client t) {

        if (t.getClientAge() < MIN_AGE || t.getClientAge() > MAX_AGE) {
            return false;
        }
        return true;

    }

}
