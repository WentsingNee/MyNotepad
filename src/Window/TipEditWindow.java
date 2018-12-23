package Window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import tipContentManager.Tip;
import tipContentManager.TipContentManager;

public class TipEditWindow {
	private Shell shell;
	private Text contentText;
	private Composite compositeTop;
	private Composite composite_1;
	private Label titleLabel;
	private Text titleText;
	private Button newTipButton;
	private Composite composite_2;
	private Composite composite_3;
	private Label emptyLabel;

	private String usr;
	private Button changeTipButton;

	private int ID_modifing;

	public TipEditWindow(String usr) {
		ID_modifing = -1;
		this.usr = usr;
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell();

		shell.setMinimumSize(new Point(500, 250));
		shell.setSize(796, 458);
		shell.setText("新建贴士");
		shell.setLayout(new BorderLayout(0, 0));
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void open(int ID) {
		Display display = Display.getDefault();
		shell = new Shell();

		shell.setMinimumSize(new Point(500, 250));
		shell.setSize(796, 458);
		shell.setText("编辑便签");
		shell.setLayout(new BorderLayout(0, 0));
		createContents();

		this.ID_modifing = ID;
		changeTipButton.setVisible(true);
		newTipButton.setVisible(false);
		System.out.println("sadfsdafdsa ");

		Tip tipOpen = TipContentManager.getTip(ID);
		titleText.setText(tipOpen.getTitle());
		contentText.setText(tipOpen.getContent());

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void createContents() {

		contentText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		contentText.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				setTipOptionButtonVisisable();
			}
		});
		contentText.setToolTipText("在此键入你的内容");
		contentText.setLayoutData(BorderLayout.CENTER);

		compositeTop = new Composite(shell, SWT.NONE);
		compositeTop.setLayoutData(BorderLayout.NORTH);
		compositeTop.setLayout(new BorderLayout(0, 0));

		composite_1 = new Composite(compositeTop, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.WEST);
		RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.wrap = false;
		rl_composite_1.center = true;
		rl_composite_1.marginBottom = 10;
		rl_composite_1.marginTop = 8;
		rl_composite_1.marginRight = 10;
		rl_composite_1.marginLeft = 10;
		rl_composite_1.spacing = 10;
		composite_1.setLayout(rl_composite_1);

		titleLabel = new Label(composite_1, SWT.NONE);
		titleLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		titleLabel.setAlignment(SWT.CENTER);
		titleLabel.setLayoutData(new RowData(73, SWT.DEFAULT));
		titleLabel.setText("标题");

		titleText = new Text(composite_1, SWT.BORDER);
		titleText.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				setTipOptionButtonVisisable();
			}
		});
		titleText.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		titleText.setToolTipText("在此键入你的标题");
		titleText.setLayoutData(new RowData(205, SWT.DEFAULT));

		composite_2 = new Composite(compositeTop, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(null);

		emptyLabel = new Label(composite_2, SWT.NONE);
		emptyLabel.setText("                   ");
		emptyLabel.setBounds(10, 3, 278, 44);

		composite_3 = new Composite(compositeTop, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.EAST);
		RowLayout rl_composite_3 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_3.wrap = false;
		rl_composite_3.marginLeft = 10;
		rl_composite_3.marginRight = 10;
		rl_composite_3.marginTop = 0;
		rl_composite_3.marginHeight = 5;
		rl_composite_3.marginWidth = 10;
		rl_composite_3.spacing = 10;
		rl_composite_3.center = true;
		composite_3.setLayout(rl_composite_3);

		changeTipButton = new Button(composite_3, SWT.NONE);
		changeTipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TipContentManager.updateTip(ID_modifing, titleText.getText(), contentText.getText());
				shell.close();
			}
		});
		changeTipButton.setLayoutData(new RowData(107, SWT.DEFAULT));
		changeTipButton.setText("修改");
		changeTipButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		changeTipButton.setEnabled(false);
		changeTipButton.setVisible(false);

		newTipButton = new Button(composite_3, SWT.NONE);
		newTipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TipContentManager.addNewTip(usr, titleText.getText(), contentText.getText());
				shell.close();
			}
		});
		newTipButton.setEnabled(false);
		newTipButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		newTipButton.setLayoutData(new RowData(113, SWT.DEFAULT));
		newTipButton.setText("添加");

	}

	void setTipOptionButtonVisisable() {
		if (titleText.getText().length() == 0 && contentText.getText().length() == 0) {
			newTipButton.setEnabled(false);
			changeTipButton.setEnabled(false);
		} else {
			newTipButton.setEnabled(true);
			changeTipButton.setEnabled(true);
		}
	}
}
