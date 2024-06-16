If a type implements the Copy trait, variables that use it do not move, but rather are trivially copied, making them still valid after assignment to another variable.

Here are some of the types that implement Copy

* All the integer types, such as u32
* The Boolean type, bool, with values true and false
* All the floating-point types, such as f64
* The character type, char
* Tuples, if they only contain types that also implement Copy. For example, (i32, i32) implements Copy, but (i32, String) does not.


when s2 and s1 go out of scope, they will both try to free the same memory. This is known as a double free error