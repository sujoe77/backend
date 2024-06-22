use std::collections::HashMap;

pub mod tests;

pub fn add(left: usize, right: usize) -> usize {
    left + right
}

pub fn minus(left: usize, right: usize) -> usize {
    left - right
}

pub fn football_group_match() -> HashMap<String, i32> {
    let mut cases: HashMap<String, i32> = HashMap::new();

    //AB, AC, AD, BC, BD, CD
    for i in 0..3_i32.pow(6) {
        let mut team_scores: [i32; 4] = [0; 4];
        let results = int_to_array(i);
        for k in 0..6 {
            set_team_score(results[k], &mut team_scores, get_team_index(k));
        }
        print!("{:?}, {:?}\n", results, team_scores);
        team_scores.sort();
        let key_str = format!("{:?}", team_scores);
        //let key= key_str.as_str();
        if cases.contains_key(&key_str) {
            let v = cases.get(&key_str).unwrap() + 1;
            cases.insert(key_str, v);
        } else {
            cases.insert(key_str, 1);
        }
    }

    print!("size is {}\n", cases.len());
    print!("{:?}\n", cases);
    let mut count = 0;
    cases.values().for_each(|c| {
        count += c;
    });
    print!("total count {}", count);
    cases
    //print!("{}\n", s);
}

fn get_team_index(k: usize) -> [usize; 2] {
    if k < 3 {
        return [0, k + 1];
    } else if k < 5 {
        return [1, k - 1];
    } else {
        return [2, 3];
    }
}

fn set_team_score(result: i32, team_scores: &mut [i32; 4], index: [usize; 2]) {
    if result == 2 {
        team_scores[index[0]] += 3;
    } else if result == 1 {
        team_scores[index[0]] += 1;
        team_scores[index[1]] += 1;
    } else {
        team_scores[index[1]] += 3;
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
