package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // 컨트롤러에서 상태 코드를 지정을 해서 내려줄 수 있게끔 해주는 컨트롤러
public class DiaryController {

	private final DiaryService diaryService;

	@PostMapping ("/create/diary") // 요청을 보낼 때 넣어주는 파라미터(날짜를 url 뒤에 파라미터로 요청할 수 있음),(바디에 데이터 넣어서 전송)
	void createDiary(@RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date, @RequestBody String text) {
		// 받은 값들을 서비스에 전달
		diaryService.createDiary(date, text);

	}

	// 일기 조회
	@GetMapping("/read/diary")
	List<Diary> readDiary(@RequestParam  @DateTimeFormat(iso= ISO.DATE) LocalDate date) {
		// 요청 파라미터로 넘어와야 하는 건 해당 날짜의 일기를 조회하는 것이므로 date 필요.
		return diaryService.readDiary(date);
	}
	// 일기 조회 - 2. 조회 날짜 범위 지정하기
	@GetMapping("/read/diaries")
	List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate startDate,
							@RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate endDate) {
		return  diaryService.readDiaries(startDate, endDate);

	}

	// 일기 수정
	@PutMapping("/update/diary") // 어떤 날짜의 일기를 수정할 것인지 ? (param) , 새로 작성할 값은 ? (RequestBody)
	void updateDiary(@RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date,
						@RequestBody String text) {

		diaryService.updateDiary(date,text);
	}

}
