require 'pathname'
class ObjectRepo
	def self.is_object_file_exist(filename)
		puts Dir.pwd
		if Pathname.new('../framework/ObjectProps/'+filename).exist?
				#$world.puts "object file #{filename} exist"
				#puts Pathname
				return File.open('../framework/ObjectProps/'+filename,"r")
			else
				puts "object file #{filename} does not exist"
				return nil
		end
	end
		
	def self.get_object_details(filename,objname)
		objFile=is_object_file_exist(filename)
		if !objFile.nil?
			objFile.each_line do |line|
				if line.include? objname
					return line
				end
			end
		end
		return nil
	end
end
#world(objectRepo)
#puts ObjectRepo.getObjectDetails("RWD/Common","rdcHeaderNavigationMenu").nil?