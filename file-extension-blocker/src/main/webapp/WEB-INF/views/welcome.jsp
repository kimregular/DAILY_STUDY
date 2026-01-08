<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Extension Blocker</title>
    <meta charset="UTF-8"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        .container {
            max-width: 600px;
        }

        a.button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 16px;
            background-color: #2c7be5;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>File Extension Blocker</h1>
    <p>
        보안 위험 파일 업로드를 방지하기 위한<br/>
        파일 확장자 차단 정책 관리 시스템입니다.
    </p>

    <a class="button" href="/extensions">
        확장자 차단 관리로 이동
    </a>
</div>
</body>
</html>