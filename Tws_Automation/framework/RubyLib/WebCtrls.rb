require_relative './Environment'
require_relative './ObjectRepo'
require 'selenium-webdriver'
require "test/unit"
require "pry"
$driver=nil
class WebCtrls
	#extend Environment
	def self.GetWebDriver(browser, os64bit)
		#xmlP=XMLParsing.new
		#xmlH=Environment.ConvertXMLToHash(ENV['EnvFileName'])
		#puts xmlH["Grid2Server"]
		case browser.downcase
			when "ff"
				$driver = Selenium::WebDriver.for :firefox
			when "rwd-ff"
				$driver = Selenium::WebDriver.for(:remote, :url => 'http://'+Environment.GetEnvValue("Grid2Server")+':4444/wd/hub', :desired_capabilities => :firefox)
			when "ie"
				proxy = Selenium::WebDriver::Proxy.new(:http => ENV['HTTP_PROXY'] || ENV['http_proxy'])
				ENV['HTTP_PROXY'] = ENV['http_proxy'] = nil
				$driver = Selenium::WebDriver.for :ie, :proxy => proxy
			when "rwd-ie"
				$driver = Selenium::WebDriver.for(:remote, :url => 'http://'+Environment.GetEnvValue("Grid2Server")+':4444/wd/hub', :desired_capabilities => :ie)
			when "gc"
				$driver = Selenium::WebDriver.for :chrome
			when "rwd-gc"
				$driver = Selenium::WebDriver.for(:remote, :url => 'http://'+Environment.GetEnvValue("Grid2Server")+':4444/wd/hub', :desired_capabilities => :chrome)
		end
		locDriver=$driver
		
		if !locDriver.nil?
				$driver.manage.window.maximize
				puts "web driver is launched for #{browser}"
				#$world.puts "web driver is launched for #{browser}"
			else
				$world.puts "web driver is not launched for #{browser}"
		end
		return $driver
	end
	
	def self.get_webelement(pagename,object_id)
		obj_deatails=ObjectRepo.get_object_details(pagename,object_id)
		#puts obj_deatails
		if !obj_deatails.nil?
			obj_locator=obj_deatails.split(',',3).at(1)
			#puts obj_locator
			
			obj_loc_val=obj_deatails.split(',',3).at(2).delete("\n")
			#puts obj_loc_val
			
			
			begin
				case obj_locator.downcase
					when "id"
						obj_web_ele=$driver.find_element(:id => obj_loc_val)
					when "class"
						obj_web_ele=$driver.find_element(:class => obj_loc_val)
					when "class_name"
						obj_web_ele=$driver.find_element(:class_name => obj_loc_val)
					when "css"
						obj_web_ele=$driver.find_element(:css => obj_loc_val)
					when "link"
						obj_web_ele=$driver.find_element(:link => obj_loc_val)
					when "link_text"
						obj_web_ele=$driver.find_element(:link_text => obj_loc_val)
					when "name"
						obj_web_ele=$driver.find_element(:name => obj_loc_val)
					when "partial_link_text"
						obj_web_ele=$driver.find_element(:partial_link_text => obj_loc_val)
					when "tag_name"
						obj_web_ele=$driver.find_element(:tag_name => obj_loc_val)
					when "xpath"
						obj_web_ele=$driver.find_element(:xpath => obj_loc_val)
				end
				
				
				return obj_web_ele
			rescue  Selenium::WebDriver::Error::NoSuchElementError
				puts "exception"
				return nil
			else
				return nil
			end
		end
		return nil
	end
	
	def self.get_webelements(pagename,object_id)
		obj_deatails=ObjectRepo.get_object_details(pagename,object_id)
		if !obj_deatails.nil?
			obj_locator=obj_deatails.split(',',3).at(1)
			obj_loc_val=obj_deatails.split(',',3).at(2).delete("\n")
			begin
				case obj_locator.downcase
					when "id"
						obj_web_ele=$driver.find_element(:id => obj_loc_val)
					when "class"
						obj_web_ele=$driver.find_element(:class => obj_loc_val)
					when "class_name"
						obj_web_ele=$driver.find_element(:class_name => obj_loc_val)
					when "css"
						obj_web_ele=$driver.find_element(:css => obj_loc_val)
					when "link"
						obj_web_ele=$driver.find_element(:link => obj_loc_val)
					when "link_text"
						obj_web_ele=$driver.find_element(:link_text => obj_loc_val)
					when "name"
						obj_web_ele=$driver.find_element(:name => obj_loc_val)
					when "partial_link_text"
						obj_web_ele=$driver.find_element(:partial_link_text => obj_loc_val)
					when "tag_name"
						obj_web_ele=$driver.find_element(:tag_name => obj_loc_val)
					when "xpath"
						obj_web_ele=$driver.find_element(:xpath => obj_loc_val)
				end
				return obj_web_ele
			rescue  Selenium::WebDriver::Error::NoSuchElementError
				return nil
			else
				return nil
			end
		end
		return nil
	end
	
	def self.browser_go_back
		$driver.navigate.back
	end	
	
	def self.browser_go_forward
		$driver.navigate.forward
	end	
	
	def self.clear(pagename,object_id)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				obj_we.clear
				return true
				else
				$world.puts "object is disabled and unable to clear value"
			end
			else
			$world.puts "object is not displayed"
		end
		return false
	end
	
	def self.enter_value(pagename,object_id,text)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				obj_we.send_keys text
				return true
				else
				$world.puts "object is disabled and unable to enter value"
			end
			else
			$world.puts "object is not displayed"
		end
		return false
	end
	
	def self.click_on_element(pagename,object_id)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				obj_we.click
				return true
				else
				$world.puts "object is disabled and unable to click on it"
			end
			else
			$world.puts "object is not displayed"
		end
		return false
	end
	
	def self.select_by_text(pagename,object_id,text)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				select_list = Selenium::WebDriver::Support::Select.new(dropdown)
				select_list.select_by(:text, text)
				return true
				else
				$world.puts "object is disabled and unable to select the item"
			end
			else
			$world.puts "object is not displayed"
		end
		return false
	end
	
	def self.select_by_index(pagename,object_id,index)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				select_list = Selenium::WebDriver::Support::Select.new(dropdown)
				select_list.select_by(:index, index)
				return true
				else
				$world.puts "object is disabled and unable to select the item"
			end
			else
			$world.puts "object is not displayed"
		end
		return false
	end
	
	def self.select_by_value(pagename,object_id,value)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				select_list = Selenium::WebDriver::Support::Select.new(dropdown)
				select_list.select_by(:index, value)
				return true
				else
				$world.puts "object is disabled and unable to select the item"
			end
			else
			$world.puts "object is not exist"
		end
		return false
	end
	
	def self.get_text(pagename,object_id,value)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				return obj_we.text
				else
				$world.puts "object is disabled and unable to get the text"
			end
			else
			$world.puts "object is not exist"
		end
		return nil
	end
	
	def self.is_displayed(pagename,object_id)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.displayed?
				return true
				else
				$world.puts "object is not displayed"
			end
			else
			$world.puts "object is not exist"
		end
		return false
	end
	
	def self.is_enabled(pagename,object_id)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				return true
				else
				$world.puts "object is not enabled"
			end
			else
			$world.puts "object is not exist"
		end
		return false
	end
	
	def self.get_attribute(pagename,object_id,attribute)
		obj_we=get_webelement(pagename,object_id)
		if !obj_we.nil?
			if obj_we.enabled?
				return obj_we.attribute(attribute)
				else
				$world.puts "object is not enabled"
			end
			else
			$world.puts "object is not exist"
		end
		return false
	end
	
	
end

# xmlP=XMLParsing.new
# xmlH=xmlP.ConvertXMLToHash('ABR.xml')
# puts xmlH['WebBrowser']
# browser1=xmlH['WebBrowser']
# #browser1=xmlH["browser"]
# puts browser1
# wbCt=WebCtrls.new
# wbCt.GetWebDriver(browser1,false)

