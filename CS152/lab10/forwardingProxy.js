// Modify the forwarding API to log all actions taken on an object.

var print = console.log

// Simplified version of code found on https://developer.mozilla.org/en-US/docs/Web/JavaScript/Old_Proxy_API
function handlerMaker(obj) {
	return {
		delete: function(name) {
			print(" > delete() " + name)
			return delete obj[name]
		},
		has: function(name) {
			print(" > has() " + name)
			return name in obj
		},
		hasOwn: function(name) {
			print(" > hasOwn() " + name)
			return Object.prototype.hasOwnProperty.call(obj, name)
		},
		get: function(receiver, name) {
			val = obj[name]
			print(" > get() " + name + " -> " + val)
			return val
		},
		set: function(receiver, name, val) {
			print(" > set() " + name + " <- " + val)
			obj[name] = val
			return true
		},
		enumerate: function() {
			var result = []
			for (name in obj)
				result.push(name)
			print(" > enumerate() [" + result + "]")
			return result
		},
		keys: function() {
			print(" > keys() [" + Object.keys(obj) + "]")
			return Object.keys(obj)
		}
	}
}

// ...

var o = Object.create({});
var proxy = Proxy.create(handlerMaker(o));

proxy.blabla = 12; // Thanks to the forwarding, o now has a 'blabla' property with a value of 12
o.blabla++; // just incrementing o.blabla

print(proxy.blabla); // prints 13: the getting operation is forwarded to o which returns 13.

proxy.a = 3
proxy.b = 2
proxy.c = 1

for (i in proxy)
	print(i)

print(Object.keys(proxy))

print(proxy.hasOwnProperty("a"))
delete(proxy.c)
print(proxy.hasOwnProperty("c"))
delete(proxy.e)

print(proxy.a + proxy.b)
