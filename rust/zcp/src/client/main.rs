use std::io::Write;
use std::net::Shutdown;
use std::net::SocketAddrV4;
use std::net::TcpStream;
use zcp::common::ADDR;
use zcp::common::PORT;
use zcp::common::{handle_stream_err, print_bytes, read_stdin, read_stream};

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
    let message = read_stdin();
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
