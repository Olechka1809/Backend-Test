package Lesson5;

import Lesson5.api.ProductService;
import Lesson5.dto.Product;
import Lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetAllProductsTest {

    int id;

    static ProductService productService;
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @SneakyThrows
    @Test
    void getProductsPositiveTest() {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
