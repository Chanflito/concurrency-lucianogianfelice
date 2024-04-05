use std::thread;
use std::thread::{scope, Thread};
use std::time::Duration;

fn hello() {
    let t1=thread::spawn(|| println!("Hello from thread 1"));
    let t2=thread::spawn(|| println!("Hello from thread 2"));
    t1.join().unwrap(); // Unwrap lo saca del box.
    t2.join().unwrap();
}

fn main() {
    println!("====== Hello Threads    =======");
    print_hello();
    println!("====== Good Bye Threads =======");

   thread::sleep(Duration::from_secs(1));// Antes de terminar main duerme
    // El thread principal se duerme.
}



fn print_hello() {
    let mut threads = Vec::new();

    for _ in 0..20 {
        threads.push( thread::spawn(|| {
            println!("Hello from thread {:?}", thread::current().id());
        }));
    }

    for thread in threads {
        thread.join().unwrap(); //Join devuelve un result, entonces tengo que hacer un unwrap.
    }
}
