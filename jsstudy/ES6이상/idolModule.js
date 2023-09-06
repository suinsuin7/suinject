//export가 중요
export const blackPink = {
    groupName: "블랙핑크",
    members: ["로제", "제니", "리사", "지수"],
    debut: "2016-08-08",
    company: "YG",
    songs: ["불장난", "마지막처럼", "Pink Venom"],
    insa: () => "Hi, This is BLACKPINK!"
}

//export가 중요
export const itzy = {
    groupName: "있지",
    members: ["채령", "예지", "리아", "류진", "유나"],
    debut: "2019-02-12",
    company: "JYP",
    songs: ["달라달라", "Not Shy", "믿지"],
    insa: () => "너희가 원하는 거 전부 있지? 있지!"
}

//default 키워드에 주목
export default function suin(pName) { alert (`${pName} 안녕`) };