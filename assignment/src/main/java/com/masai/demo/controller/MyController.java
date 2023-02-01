package com.masai.demo.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.demo.model.Data;
import com.masai.demo.model.Entry;
import com.masai.demo.model.ResponseDTOOfData;

@RestController
public class MyController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	//  Create an API that lists the title, description based on the category passed as an input parameter.
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<Object> getEntriesByCategoriesHandler(@PathVariable("category") String category){
		
		String url = "https://api.publicapis.org/entries";
		
		Data data = restTemplate.getForObject(url, Data.class);
		
		List<Entry> list = data.getEntries();
		
		List<ResponseDTOOfData> dtos = list.stream().filter(x -> x.getCategory()!=category).map(x -> new ResponseDTOOfData(x.getApi(),x.getDescription())).collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
	
	
	//  Create an API that would save a new entry with all the relevant properties which retrieves values from the endpoint GET /entries.
	
	
	@GetMapping("/entries")
	public ResponseEntity<Object> getEntriesByCategoriesHandler(@RequestBody Entry entry){
		
		String url = "https://api.publicapis.org/entries";
		
		Data data = restTemplate.getForObject(url, Data.class);
		
		List<Entry> list = data.getEntries();
		
		list.add(entry);
		
		
		return new ResponseEntity<Object>(list,HttpStatus.ACCEPTED);
		
		
		
	}
}
