/**
 * 
 */
function infoConfirm() {
	if (document.reg_frm.id.value.legth==0){
	 /* document=문서 전체 */
		alert("아이디는 필수 입니다.");
		reg_frm.id.focus();
		return;
	}
	
	if (document.reg_frm.id.value.legth<4) { 
		alert("아이디는 4글자 이상이어야 합니다");
		reg_frm.id.focus();
		return;
	}
	
	if (document.reg_frm.pw.value.legth == 0) { 
		alert("비밀번호 입력은 필수 입니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if (document.reg_frm.pw.value != document.reg_frm.pw_check.value) { 
		alert("비밀번호가 일치 하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if (document.reg_frm.name.value.legth == 0) { 
		alert("이름 입력은 필수 입니다.");
		reg_frm.name.focus();
		return;
	}
	
	if (document.reg_frm.eMail.value.legth == 0) { 
		alert("이메일 입력은 필수 입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	if (document.reg_frm.address.value.legth == 0) { 
		alert("주소 입력은 필수 입니다.");
		reg_frm.address.focus();
		return;
	}
	
}