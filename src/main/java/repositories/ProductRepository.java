package repositories;

import model.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//to remember (stereotype annotations are not added to the context automatically
@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addProduct(Product product){
        String sql= "INSERT INTO product VALUES (NULL, ?,?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    //RowMapper helps us in mapping the ResultSet from JDBC into an Object
    public List<Product> getProducts(){
        String sql= "SELECT * FROM product";
        return jdbcTemplate.query(sql, new RowMapper<Product>() {

            @Override
            public @Nullable Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                return product;
            }
        });
    }
}
