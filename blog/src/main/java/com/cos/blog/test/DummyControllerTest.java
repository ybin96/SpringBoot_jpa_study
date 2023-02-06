package com.cos.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;


@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입
	private UserRepository userrepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userrepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패";
		}
		return "삭제 ID:"+id;
	}
	
	@Transactional // 함수 종류시 자동 commit
	@PostMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("id : "+requestUser.getPassword());
		System.out.println("id : "+requestUser.getEmail());
		
		User user = userrepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userrepository.save(requestUser);
		// 더티 체킹 
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userrepository.findAll();
	}

	// 한 페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2,sort = "id", direction = Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userrepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
//	@GetMapping("/dummy/user/{id}")
//	public User detail(@PathVariable int id) {
//		Optional<User> findById = userrepository.findById(id);
//		if(findById.isPresent()) {
//			User user = findById.get();
//			return user;
//		} else {
//			return null;
//		}
//	}

//	// 람다식
//	@GetMapping("/dummy/user/{id}")
//	public User detail1(@PathVariable int id) {
//		User user = userrepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
//
//		return user;
//	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userrepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated
				return new IllegalArgumentException("해당 유저는 없습니다. ID:" + id);
			}
		});
		
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getEmail());

		user.setRole(RoleType.USER);
		userrepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
