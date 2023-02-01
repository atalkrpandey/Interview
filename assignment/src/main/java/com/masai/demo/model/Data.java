package com.masai.demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {

	private String title;
	
	private List<Entry> entries = new ArrayList<>();
}
