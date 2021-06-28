package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import model.Web_Series;
import repository.Web_Series_Repository;

@RestController
public class WebSeries 
{
	@Autowired
	Web_Series_Repository web_series_repo;
	
	@GetMapping("/")
	public String Home() 
	{
		return "Rest Server Started ";
	}
	
	@GetMapping("/series")
	public List<Web_Series> getSeries()
	{
		return web_series_repo.findAll();
	}

	@PostMapping("/addSeries")
	public Web_Series saveSeries(@RequestBody Web_Series web_series_m)
	{
		return web_series_repo.save(web_series_m);
	}
	
	@PostMapping("/update")
	public Web_Series editSeries(@RequestBody Web_Series web_Series_model,@RequestParam String id)
	{
		web_Series_model.setId(id);
		return web_series_repo.save(web_Series_model);
	}
	
	@PostMapping("/delete")
	public String deleteSeries(@RequestParam String id)
	{
		Web_Series web_Series_model=web_series_repo.getOne(id);
		web_series_repo.delete(web_Series_model);
		return "Series Removed "+id;
	}
	
	@PostMapping("/series")
	public Optional<Web_Series> getById(@RequestParam String id)
	{
		return web_series_repo.findById(id);
	}
	
	@PostMapping("/series/name")
	public Web_Series updateByName(@RequestBody Web_Series web_Series,@RequestParam String name)
	{
		web_Series.setName(name);
		return web_series_repo.save(web_Series);
	}
}
