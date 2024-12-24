use std::net::{Ipv4Addr, SocketAddrV4};

#[test]
fn get_local_ips() {
    let parts = "192.168.10.3".split(".");
    let collection = parts.collect::<Vec<&str>>();
    let addr: Ipv4Addr = Ipv4Addr::new(
        to_number(collection[0]),
        to_number(collection[1]),
        to_number(collection[2]),
        to_number(collection[3]),
    );
    println!("{}", addr);
}
