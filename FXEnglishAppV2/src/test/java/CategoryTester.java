
import com.dht.pojo.Category;
import com.dht.services.CategoryServices;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duonghuuthanh
 */
public class CategoryTester {
    @Test
    public void testUniqueNames() {
        CategoryServices service = new CategoryServices();
        List<Category> actual = service.getCategories();
        
        List<String> kq = new ArrayList<>();
        actual.forEach(c -> kq.add(c.getName()));
        
        Set<String> kq2 = new HashSet<>(kq);
        
        Assertions.assertEquals(kq.size(), kq2.size());
    }
}
