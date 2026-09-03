import {test, expect} from '@playwright/test';

test('user can submit the Selenium web form', async ({page}) => {
        await page.goto(
            'https://www.selenium.dev/selenium/web/web-form.html'
        );

        await page.getByLabel('Text input').fill('Sudhir');
        await page.getByLabel('Password').fill('playwright123');
        await page.getByLabel('Textarea').fill('Learning Playwright automation');

        await page.getByLabel('Dropdown (select)').selectOption('2');
        await page.getByLabel('Default checkbox').check();
        await page.getByLabel('Default radio').check();

        await page.getByRole('button', {name: 'Submit'}).click();

        await expect(page).toHaveURL(/submitted-form/);
        await expect(page.getByRole('heading')).toHaveText('Form submitted');
        await expect(page.getByText('Received!')).toBeVisible();
    }
)