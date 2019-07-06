# Java Apache POI
<br>

----------------------------------------
jar檔載點 : https://mvnrepository.com/artifact/org.apache.poi/poi

這裡使用 Apache POI 3.5

----------------------------------------
<br>

## 一、 學習如何用 POI 產出 excel 報表。

* 創建一個 Workbook，POI 使用 Workbook 對 excel 進行建置。

        public class POI {
            public static void main(String[] args) throws Exception {
                Workbook excel = new HSSFWorkbook();
                FileOutputStream fileOut = new FileOutputStream("C:\\git\\workbook.xls");
                excel.write(fileOut);
                fileOut.close();                
                System.out.println("檔案輸出完成 !");
        }

    <br>

* 創建一個工作表 (sheet)。 

        public class POI {
            public static void main(String[] args) throws Exception {
                Workbook wb = new HSSFWorkbook();
                Sheet sheet1 = wb.createSheet("new sheet");
                Sheet sheet2 = wb.createSheet("second sheet");

                String safeName = WorkbookUtil.createSafeSheetName("[Johnny's sheet]"); 
                Sheet sheet3 = wb.createSheet(safeName);

                FileOutputStream fileOut = new FileOutputStream("C:\\git\\workbook02.xls");
                wb.write(fileOut);
                fileOut.close();
                
                System.out.println("輸出成功 ! ");
            }


    sheet 是 workbook 的下一級對象，是工作表。創建sheet時要注意命名約束。使用 WorkbookUtil.createSafeSheetName() 來命名相對安全。

<br>

* 創建表格內容 

        public class POI {
            public static void main(String[] args) throws Exception {
                Workbook wb = new HSSFWorkbook();
                CreationHelper createHelper = wb.getCreationHelper();
                Sheet sheet = wb.createSheet("new sheet");

                // 創建一欄(row)
                Row row = sheet.createRow(0);
                // 在 row 裡放入一列(cell)
                Cell cell = row.createCell(0);
                //在列裡放入值
                cell.setCellValue(1);

                // 另一種寫法 : 
                row.createCell(1).setCellValue(1.2);
                row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
                row.createCell(3).setCellValue(true);
                
                //第2欄
                Row row1 = sheet.createRow(1);
                row1.createCell(0).setCellValue("This");
                row1.createCell(1).setCellValue("is");
                row1.createCell(2).setCellValue("second");
                row1.createCell(3).setCellValue("row");

                // 匯出
                FileOutputStream fileOut = new FileOutputStream("c:\\git\\workbook06.xls");
                wb.write(fileOut);
                fileOut.close();
                
                System.out.println("輸出完成!");
            }
        }

    Row 是歸屬於 sheet 對象的下級，Cell 是歸屬於 Row 對象的下級。

<br>

-----------------------------------------------------

<br>

 ## 二、 學習如何用 POI 讀入 excel 報表

 * 將報表讀入程式中 

        public class POI {
            public static void main(String[] args) throws Exception {
                InputStream inp = null;
                try {
                    inp = new FileInputStream("c:\\git\\workbook04.xls");
                    Workbook wb = new HSSFWorkbook(inp);
                    Sheet sheet = wb.getSheetAt(0);
                    Iterator<Row> rowIterator = sheet.rowIterator();
                    while (rowIterator.hasNext()) {
                        Row r = rowIterator.next();
                        if (r == null) {
                            System.out.println("Empty Row");
                            continue;
                        }
                        for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                            Cell cell = r.getCell(i);
                            String cellValue = "";
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    cellValue = cell.getRichStringCellValue().getString();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue().toString();
                                    } else {
                                        cellValue = String.valueOf(cell.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    cellValue = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    cellValue = String.valueOf(cell.getCellFormula());
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    break;
                                default:
                            }
                            System.out.println("CellNum:" + i + " => CellValue:" + cellValue);
                        }
                    }
                } finally {
                    if (inp != null) {
                        inp.close();
                    }
                }
            }
        }