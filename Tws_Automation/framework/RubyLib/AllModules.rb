require_relative './Environment.rb'
require_relative './ObjectRepo.rb'
require_relative './WebCtrls.rb'
class AllModules
  include Environment
  #include ObjectRepo
  include WebCtrls
 end