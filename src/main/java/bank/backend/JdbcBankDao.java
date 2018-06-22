package bank.backend;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

//@Repository
public class JdbcBankDao implements  BankDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcBankDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addClient(Client client) {
        jdbcTemplate.update("insert into clients(name, balance) values(?,?)", client.getName(), client.getBalance());
    }

    @Override
    public List<Client> listClients() {
        return jdbcTemplate.query("select id, name, balance from clients",
                (rs, i) ->
                        new Client(rs.getLong("id"), rs.getString("name"),
                                rs.getLong("balance"))
        );
    }
}
