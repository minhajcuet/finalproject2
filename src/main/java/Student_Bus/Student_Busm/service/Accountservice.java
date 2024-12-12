package Student_Bus.Student_Busm.service;

import Student_Bus.Student_Busm.entity.Account;
import Student_Bus.Student_Busm.repository.Accountrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Accountservice {

    private final Accountrepository accountrepository;

    public Account saveAccount(Account account) {
        return accountrepository.save(account); // JpaRepository's save method
    }

     //Retrieve an account by student ID
    public Optional<Account> getAccountById(String studentId) {
        return accountrepository.findById(studentId); // JpaRepository's findById method
    }

    public Account postaccount(Account account) {
        return accountrepository.save(account);
    }

    public Account findByStudentIdAndPassword(String studentId, String password) {
        Optional<Account> optionalAccount = accountrepository.findByStudentIdAndPassword(studentId, password);
        return optionalAccount.orElse(null);  // Return null if no account is found
    }


}
