package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int user_id; // user_id INT PK NN
	private String phone; //phone VARCHAR(45) NN
	private String user_name; //user_name VARCHAR(50) NN
	private String gender; //gender VARCHAR(45) NN
	private String birth_date; //birth_date DATE NN
	private String address; //address VARCHAR(255) NN
	private int food_category_id; //food_category_id INT
	private String created_at; //created_at TMESTAMP(3)
	private String email; //email NN VARCHAR(50)
	private int point; //point INT
	private String password; //password NN VARCHAR(45)
}
