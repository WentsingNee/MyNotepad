package Window;

import java.time.LocalDate;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import tipContentManager.Tip;
import tipContentManager.TipContentManager;

public class UsrMainPage {
	private Table table;
	private String usr;
	private TableColumn idColumn;
	private TableColumn titleColumn;
	private TableColumn createDateColumn;
	private TableColumn contentColumn;
	private Button openTipButton;
	private Button deleteTipButton;
	private Button newTipButton;
	private Composite composite;

	public UsrMainPage(String usr) {
		this.usr = usr;
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlMyNotepad = new Shell();
		shlMyNotepad.setSize(842, 562);
		shlMyNotepad.setText("My Notepad");
		shlMyNotepad.setLayout(new FormLayout());

		table = new Table(shlMyNotepad, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem select = table.getSelection()[0];
				openTipButton.setEnabled(true);
				deleteTipButton.setEnabled(true);
			}
		});
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.right = new FormAttachment(100, -10);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		table.setToolTipText("");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		idColumn = new TableColumn(table, SWT.NONE);
		idColumn.setWidth(104);
		idColumn.setText("编号");

		titleColumn = new TableColumn(table, SWT.NONE);
		titleColumn.setWidth(100);
		titleColumn.setText("标题");

		createDateColumn = new TableColumn(table, SWT.NONE);
		createDateColumn.setWidth(134);
		createDateColumn.setText("创建日期");

		contentColumn = new TableColumn(table, SWT.LEFT);
		contentColumn.setWidth(320);
		contentColumn.setText("内容摘要");

		composite = new Composite(shlMyNotepad, SWT.NONE);
		fd_table.top = new FormAttachment(composite, 3);
		RowLayout rl_composite = new RowLayout(SWT.HORIZONTAL);
		rl_composite.marginTop = 5;
		rl_composite.center = true;
		rl_composite.spacing = 10;
		rl_composite.wrap = false;
		composite.setLayout(rl_composite);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -458);
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 24);
		fd_composite.right = new FormAttachment(100, -118);
		composite.setLayoutData(fd_composite);

		openTipButton = new Button(composite, SWT.NONE);
		openTipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMyNotepad.setVisible(false);
				TableItem select = table.getSelection()[0];
				new TipEditWindow(usr).open(Integer.decode(select.getText(0)).intValue());
				updateTips();
				shlMyNotepad.setVisible(true);
				shlMyNotepad.setActive();
			}
		});
		openTipButton.setLayoutData(new RowData(80, 33));
		openTipButton.setEnabled(false);
		openTipButton.setText("打开");

		deleteTipButton = new Button(composite, SWT.NONE);
		deleteTipButton.setLayoutData(new RowData(80, 33));
		deleteTipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelection().length == 0) {
					openTipButton.setEnabled(false);
					deleteTipButton.setEnabled(false);
				} else {
					for (TableItem select : table.getSelection()) {
						TipContentManager.remove(Integer.decode(select.getText(0)).intValue());
					}
					updateTips();
					openTipButton.setEnabled(false);
					deleteTipButton.setEnabled(false);
				}
			}
		});
		deleteTipButton.setEnabled(false);
		deleteTipButton.setText("删除");

		newTipButton = new Button(composite, SWT.NONE);
		newTipButton.setLayoutData(new RowData(80, 33));
		newTipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMyNotepad.setVisible(false);
				new TipEditWindow(usr).open();
				updateTips();
				shlMyNotepad.setVisible(true);
				shlMyNotepad.setActive();
			}
		});
		newTipButton.setEnabled(true);
		newTipButton.setText("新建");

		updateTips();

		shlMyNotepad.open();
		shlMyNotepad.layout();
		while (!shlMyNotepad.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	void updateTips() {

		table.removeAll();

		Vector<Tip> tipList = TipContentManager.getAllTipsOf(usr);
		for (Tip tip : tipList) {

			LocalDate createDate = tip.getCreateDate();
			String content = tip.getContent();
			if (content.length() > 20) {
				content = content.substring(0, 20) + "...";
			}

			new TableItem(table, SWT.NONE)
					.setText(new String[] { String.valueOf(tip.ID), tip.getTitle(), createDate.toString(), content });
		}
	}
}
