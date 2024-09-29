#[test]
fn test_regex_func() {
    use sujoe_lib::str::regex_func;
    regex_func("2024-01-01");
}

#[test]
fn test_show_text() {
    use sujoe_lib::str::show_text;
    show_text("show_text is called!");
}

#[test]
fn test_print_str() {
    use sujoe_lib::str::print_str;
    print_str();
}
