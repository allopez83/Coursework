var incompleteHandler = {get:function(myProxy, name){
                               console.log('Property ' + name + ' accessed.');
                               return 1;
                             }
                        };
var p = Proxy.create(incompleteHandler);
var q = p.hello; // displays 'Property hello accessed' and 1 is assigned to q
//p.goodbye = "Trying to set a property"; //

