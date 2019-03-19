require 'rexml/document'
include REXML

class Environment
#extend self

def self.GetEnvValue(env_var_name)
	xmlH=ConvertXMLToHash(ENV['EnvFileName'])
	return xmlH[env_var_name]
end



def self.ConvertXMLToHash(xmlfile)
	env = Hash.new
	xmlFile=GetEnvironmentXMLFile(xmlfile)
	if !xmlFile.nil?
		#$world.puts "file exists"
		rootXF=Document.new(File.new(xmlFile))
		rootXF.each_element('//Variable') {|var| 
		#puts var.elements["Name"].text
		#puts var.elements["Value"].text
		env[var.elements["Name"].text]=var.elements["Value"].text
		}
		#puts env
		Dir.chdir @localPath
		#$world.puts Dir.pwd
		return env
	end
	
end

def self.GetEnvironmentXMLFile(xmlfile) 
	@localPath= Dir.pwd
	#$world.puts @localPath
	#puts File.expand_path("../framework/Environment")
	Dir.chdir "../framework/Environment"
	if File.exists?(xmlfile)
		#$world.puts "environment file"+xmlfile+" exists"
		#Dir.chdir localPath
		#puts Dir.pwd
		return File.open(xmlfile,"r")
	
		else
		$world.puts "environment file"+xmlfile+" does not exist"
	end
end
end

#xmlP=XMLParsing.new 
#puts xmlP.ConvertXMLToHash('ABR.xml')

