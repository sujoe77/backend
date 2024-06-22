use core::num;

pub mod tests;

pub fn add(left: usize, right: usize) -> usize {
    left + right
}

pub fn minus(left: usize, right: usize) -> usize {
    left - right
}

pub fn football_group_match() {
    //AB, AC, AD, BC, BD, CD
    for i in 0..216 {
        let array = int_to_array(i);
        let mut team_scores = [0, 0, 0, 0];
        for k in 0..6 {
            if k < 3 {
                if array[k] == 2 {
                    team_scores[0] += 3;
                } else if array[k] == 1 {
                    team_scores[0] += 1;
                    team_scores[k + 1] += 1;
                } else {
                    team_scores[k + 1] += 3;
                }
            } else if k < 5 {
                if array[k] == 1 {
                    team_scores[1] += 3;
                } else if array[k] == 0 {
                    team_scores[1] += 1;
                    team_scores[k - 1] += 1;
                } else {
                    team_scores[k - 1] += 3;
                }
            } else {
                if array[k] == 1 {
                    team_scores[2] += 3;
                } else if array[k] == 0 {
                    team_scores[2] += 1;
                    team_scores[3] += 1;
                } else {
                    team_scores[3] += 3;
                }
            }
        }
        print!("{:?}", array);
        print!("{:?}\n", team_scores);
    }
}

fn int_to_array(input: i32) -> [i32; 6] {
    let mut ret: [i32; 6] = [0; 6];
    let mut number = input;
    let mut i = 5;

    while number >= 3 {
        ret[i] = number % 3;
        number = number / 3;
        i -= 1;
    }
    ret[i] = number;
    ret
}
