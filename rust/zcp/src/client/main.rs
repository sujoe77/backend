use std::io::Write;
use std::net::Shutdown;
use std::net::TcpStream;
use std::net::{Ipv4Addr, SocketAddrV4};
use zcp::common::{handle_stream_err, print_bytes, read_stdin, read_stream};

const ADDR: Ipv4Addr = Ipv4Addr::new(192, 168, 50, 98);
const PORT: u16 = 8000;

fn main() -> std::io::Result<()> {
    if let Ok(stream) = TcpStream::connect(SocketAddrV4::new(ADDR, PORT)) {
        println!(
            "Connected to the server on {:?}",
            stream.peer_addr().unwrap()
        );
        while handle_input(&stream) {
            read_from_server(&stream);
        }
        stream.shutdown(Shutdown::Both).expect("Shutdown Failed!");
    } else {
        println!("Couldn't connect to server...");
    }

    Ok(())
}

fn handle_input(mut stream: &TcpStream) -> bool {
    //println!("--------- start reading from stdin");
    let message = read_stdin();
    //println!("--------- got {:?} from stdin", message);
    match message.as_str() {
        "#END#\n" => false,
        _ => {
            let _ = stream.write(&message.into_bytes());
            true
        }
    }
}

fn read_from_server(stream: &TcpStream) {
    read_stream(stream, print_bytes, handle_stream_err);
}
