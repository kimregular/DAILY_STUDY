<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>확장자 차단 관리</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        .container {
            max-width: 900px;
        }

        .section {
            margin-top: 28px;
            padding-top: 16px;
            border-top: 1px solid #ddd;
        }

        .row {
            display: flex;
            gap: 12px;
            align-items: center;
        }

        .fixed-grid {
            display: grid;
            grid-template-columns: repeat(4, minmax(140px, 1fr));
            gap: 10px;
            margin-top: 12px;
        }

        .fixed-item {
            padding: 10px;
            border: 1px solid #e3e3e3;
            border-radius: 6px;
        }

        .custom-box {
            margin-top: 12px;
        }

        input[type="text"] {
            padding: 10px;
            width: 320px;
        }

        button {
            padding: 10px 14px;
            cursor: pointer;
        }

        .pill {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 6px 10px;
            border: 1px solid #e3e3e3;
            border-radius: 999px;
            margin: 6px 8px 0 0;
        }

        .xbtn {
            border: none;
            background: transparent;
            cursor: pointer;
            font-weight: bold;
        }

        .muted {
            color: #666;
            font-size: 13px;
        }

        .error {
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #f3b0b0;
            background: #fff2f2;
            border-radius: 6px;
            color: #8a1f1f;
            display: none;
        }

        .success {
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #b0f3c6;
            background: #f2fff7;
            border-radius: 6px;
            color: #1f6b3a;
            display: none;
        }

        a {
            color: #2c7be5;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: baseline;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="topbar">
        <h1>확장자 차단 관리</h1>
        <div class="muted"><a href="/">홈</a></div>
    </div>

    <p class="muted">
        고정 확장자 체크/해제는 즉시 저장됩니다. 커스텀 확장자는 최대 200개까지 추가 가능합니다.
    </p>

    <div id="alertError" class="error"></div>
    <div id="alertSuccess" class="success"></div>

    <!-- 1) 고정 확장자 -->
    <div class="section">
        <h2>고정 확장자</h2>
        <div class="fixed-grid">
            <c:forEach var="p" items="${fixedList}">
                <div class="fixed-item">
                    <label class="row">
                        <input
                                type="checkbox"
                                class="fixed-checkbox"
                                data-id="${p.id}"
                                <c:if test="${p.blocked}">checked</c:if>
                        />
                        <span><c:out value="${p.name}"/></span>
                    </label>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- 2) 커스텀 확장자 -->
    <div class="section">
        <h2>커스텀 확장자</h2>

        <div class="row custom-box">
            <input id="customInput" type="text" maxlength="20" placeholder="예: sh, exe, tar_gz (최대 20자)"/>
            <button id="btnAdd" type="button">추가</button>
            <span class="muted">개수: <span id="customCount">${customCount}</span> / 200</span>
        </div>

        <div id="customList" style="margin-top: 10px;">
            <c:forEach var="cst" items="${customList}">
                <span class="pill" data-id="${cst.id}">
                    <span class="name"><c:out value="${cst.name}"/></span>
                    <button class="xbtn custom-delete" type="button" data-id="${cst.id}" aria-label="delete">X</button>
                </span>
            </c:forEach>
        </div>
    </div>

    <!-- 3) 파일 업로드 테스트 -->
    <div class="section">
        <h2>파일 업로드</h2>
        <div class="row">
            <input id="fileInput" type="file" style="display: none;"/>
            <button id="btnFileSelect" type="button">파일 선택</button>
            <span id="fileName" class="muted">선택된 파일 없음</span>
            <button id="btnUpload" type="button">업로드</button>
        </div>
        <div id="uploadResult" style="margin-top: 10px; font-weight: bold;"></div>
    </div>
</div>

<script>
    // ---- util ----
    const alertError = document.getElementById("alertError");
    const alertSuccess = document.getElementById("alertSuccess");

    function showError(msg) {
        alertSuccess.style.display = "none";
        alertError.textContent = msg;
        alertError.style.display = "block";
    }

    function showSuccess(msg) {
        alertError.style.display = "none";
        alertSuccess.textContent = msg;
        alertSuccess.style.display = "block";
        setTimeout(() => {
            alertSuccess.style.display = "none";
        }, 1500);
    }

    async function readErrorMessage(resp) {
        try {
            const data = await resp.json();
            if (data && data.message) return data.message;
        } catch (e) {
        }
        return "요청 처리에 실패했습니다.";
    }

    function normalizeClient(raw) {
        if (raw == null) return "";
        let s = raw.trim();
        while (s.startsWith(".")) s = s.substring(1);
        s = s.trim().toLowerCase();
        return s;
    }

    function setCount(delta) {
        const el = document.getElementById("customCount");
        const v = parseInt(el.textContent || "0", 10);
        el.textContent = String(v + delta);
    }

    // ---- 1) fixed toggle ----
    document.querySelectorAll(".fixed-checkbox").forEach(cb => {
        cb.addEventListener("change", async () => {
            const checkbox = cb;
            const id = checkbox.getAttribute("data-id"); // dataset 대신 getAttribute 시도
            const blocked = checkbox.checked;

            if (!id || id === "undefined" || id === "") {
                console.error("ID가 누락되었습니다.");
                checkbox.checked = !blocked;
                showError("고정 확장자 ID를 읽지 못했습니다.");
                return;
            }

            try {
                const resp = await fetch("/api/extensions/fixed/" + id, {
                    method: "PATCH",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ blocked })
                });

                if (!resp.ok) {
                    const msg = await readErrorMessage(resp);
                    checkbox.checked = !blocked;
                    showError(msg);
                    return;
                }

                showSuccess("저장되었습니다.");
            } catch (err) {
                checkbox.checked = !blocked;
                showError("네트워크 오류로 저장에 실패했습니다.");
            }
        });
    });

    // ---- 2) custom add ----
    const input = document.getElementById("customInput");
    const btnAdd = document.getElementById("btnAdd");
    const listEl = document.getElementById("customList");

    async function addCustom() {
        const raw = input.value;
        const name = normalizeClient(raw);

        if (!name) {
            showError("확장자를 입력하세요.");
            return;
        }
        if (name.length > 20) {
            showError("확장자 최대 길이는 20자리입니다.");
            return;
        }

        btnAdd.disabled = true;

        try {
            const resp = await fetch(`/api/extensions/custom`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({name})
            });

            if (!resp.ok) {
                const msg = await readErrorMessage(resp);
                showError(msg);
                return;
            }

            const data = await resp.json(); // {id, name}
            const pill = document.createElement("span");
            pill.className = "pill";
            pill.dataset.id = data.id;

            pill.innerHTML = `
                <span class="name"></span>
                <button class="xbtn custom-delete" type="button" data-id="${data.id}" aria-label="delete">X</button>
            `;
            pill.querySelector(".name").textContent = data.name;

            listEl.appendChild(pill);
            setCount(+1);
            input.value = "";
            showSuccess("추가되었습니다.");
        } catch (err) {
            showError("네트워크 오류로 추가에 실패했습니다.");
        } finally {
            btnAdd.disabled = false;
        }
    }

    btnAdd.addEventListener("click", addCustom);
    input.addEventListener("keydown", (e) => {
        if (e.key === "Enter") addCustom();
    });

    // ---- 3) custom delete (event delegation) ----
    listEl.addEventListener("click", async (e) => {
        const btn = e.target.closest(".custom-delete");
        if (!btn) return;

        const id = btn.dataset.id;
        const pill = btn.closest(".pill");

        try {
            const resp = await fetch("/api/extensions/custom/" + id, {method: "DELETE"});

            if (!resp.ok) {
                const msg = await readErrorMessage(resp);
                showError(msg);
                return;
            }

            pill.remove();
            setCount(-1);
            showSuccess("삭제되었습니다.");
        } catch (err) {
            showError("네트워크 오류로 삭제에 실패했습니다.");
        }
    });

    // ---- 4) file upload ----
    const fileInput = document.getElementById("fileInput");
    const btnFileSelect = document.getElementById("btnFileSelect");
    const fileName = document.getElementById("fileName");
    const btnUpload = document.getElementById("btnUpload");
    const uploadResult = document.getElementById("uploadResult");

    btnFileSelect.addEventListener("click", () => {
        fileInput.click();
    });

    fileInput.addEventListener("change", () => {
        if (fileInput.files.length > 0) {
            fileName.textContent = fileInput.files[0].name;
        } else {
            fileName.textContent = "선택된 파일 없음";
        }
        uploadResult.textContent = "";
    });

    btnUpload.addEventListener("click", async () => {
        if (fileInput.files.length === 0) {
            alert("파일을 선택해주세요.");
            return;
        }

        const formData = new FormData();
        formData.append("file", fileInput.files[0]);

        btnUpload.disabled = true;
        uploadResult.textContent = "확인 중...";
        uploadResult.style.color = "black";

        try {
            const resp = await fetch("/api/extensions/upload-check", {
                method: "POST",
                body: formData
            });

            if (!resp.ok) {
                const msg = await readErrorMessage(resp);
                uploadResult.textContent = "에러: " + msg;
                uploadResult.style.color = "#8a1f1f";
                return;
            }

            const data = await resp.json(); // {allowed, message}
            uploadResult.textContent = data.message;
            uploadResult.style.color = data.allowed ? "#1f6b3a" : "#8a1f1f";

        } catch (err) {
            uploadResult.textContent = "네트워크 오류가 발생했습니다.";
            uploadResult.style.color = "#8a1f1f";
        } finally {
            btnUpload.disabled = false;
        }
    });
</script>
</body>
</html>